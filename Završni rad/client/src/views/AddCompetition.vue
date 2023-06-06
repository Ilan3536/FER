<template>
    <v-row justify="center" class="margin">
      <v-dialog
        v-model="dialog"
        scrollable
        width="80%"
      >
        <template v-slot:activator="{ props }">
            <v-btn
                color="teal-darken-1"
                v-bind="props"
                icon="mdi-plus"
                size="x-large"
            >
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="centered-text"> 
            Add new competition
            </v-card-title>
          <v-divider></v-divider>
          <v-card
                class="mx-auto list-container"
                width="100%"
            >
            <v-container class="container-center scroll-container">
                
                
                <AddForm />

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
import { getCurrentInstance } from 'vue';
import AddForm from './AddForm.vue'



export default {
    name: 'AddCompetition',
    components:{
        AddForm
    },
    data(){
        return {
            dialogm1: '',
            dialog: false,
            headers: [
                { text: 'Place', value: 'place', sortable: true },
                { text: 'Name', value: 'osoba.imeosoba', sortable: true },
                { text: 'Surname', value: 'osoba.prezimeosoba', sortable: true },
                { text: 'Time', value: 'vrijeme', sortable: true },
                { text: 'Points', value: 'bodovi', sortable: true },
                { text: 'Year born', value: 'godina', sortable: true },
                
            ],
        }
    },
    computed: {
        ...mapState('rezultati', ['eventResults']),
        eventResultsFormatted(){
            let i = 1
            return this.eventResults.map(item => {
                return  {
                    ...item,
                    vrijeme : this.formatTime(item.vrijeme),
                    godina: this.formatYear(item.osoba.datumrodjenja),
                    place: i++,
                }
            })
        }
    },
    created(){
        this.fetchResultsByCompetitionAndEvent( {
            idn: this.idnatjecanje, 
            idd: this.iddisciplina
        })
        this.checkEventType()
        
    },
    methods: {
        ...mapActions('rezultati', ['fetchResultsByCompetitionAndEvent']),
        formatTime(time){
            const $formatTime = getCurrentInstance().appContext.config.globalProperties.$formatTime
            return $formatTime(time)
        },
        formatYear(time){
            const $formatYear = getCurrentInstance().appContext.config.globalProperties.$formatYear
            return $formatYear(time)
        },
        checkEventType(){
          if (this.vrstanatjecanje=='world championships' || this.vrstanatjecanje=='olmypic games' || this.vrstanatjecanje=='olympic trials'){
            this.headers.push({ text: 'Country', value: 'osoba.drzava.nazivdrzava', sortable: true })
          } else {
            this.headers.push({ text: 'Club', value: 'osoba.klub.nazivklub', sortable: true })
          }
        }
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

.scroll-container {
  overflow-y: auto;
  max-height: 600px; 
}

</style>