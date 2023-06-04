<template>
    <v-row justify="center" class="margin">
      <v-dialog
        v-model="dialog"
        scrollable
        width="80%"
      >
        <template v-slot:activator="{ props }">
          <v-btn
            color="teal-darken-2"
            v-bind="props"

            >
            Event Results
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="centered-text"> {{ eventResults[0].disciplina.nazivdisciplina + ' ' + eventResults[0].disciplina.spol }}</v-card-title>
          <v-divider></v-divider>
          <v-card
                class="mx-auto list-container"
                width="100%"
            >
            <v-container class="container-center">
                <EasyDataTable
                    :headers="headers"
                    :items="eventResults"
                    buttons-pagination
                />
            </v-container>
            </v-card>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
</template>
<script>
import { mapActions, mapState } from 'vuex';

export default {
    name: 'EventResults',
    components:{
        EasyDataTable: window['vue3-easy-data-table'],
    },
    props: {
        idnatjecanje: Number,
        iddisciplina: Number,
    },
    data(){
        return {
            dialogm1: '',
            dialog: false,
            headers: [
                { text: 'Id', value: 'idrezultat', sortable: true },
                { text: 'Name', value: 'osoba.imeosoba', sortable: true },
                { text: 'Surname', value: 'osoba.prezimeosoba', sortable: true },
                { text: 'Time', value: 'vrijeme', sortable: true },
            ],
        }
    },
    computed: {
        ...mapState('rezultati', ['eventResults'])
    },
    created(){
        this.fetchResultsByCompetitionAndEvent( {
            idn: this.idnatjecanje, 
            idd: this.iddisciplina
        })
    },
    methods: {
        ...mapActions('rezultati', ['fetchResultsByCompetitionAndEvent'])
    }
    
}
</script>
<style scoped>
.margin {
    margin-top: -4px;
}
.centered-text {
    margin: 10px;
    display: flex;
    justify-content: center;
    align-items: center; 
}
</style>