<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        scrollable
        width="50%"
      >
        <template v-slot:activator="{ props }">
          <v-btn
            color="teal-darken-2"
            v-bind="props"
          >
            Qualifying time standards
          </v-btn>
        </template>
        <v-card>
          <v-card-title>Qualifiying time standards for National Championship 2023
          </v-card-title>
          <v-divider></v-divider>
          <v-card
                class="mx-auto list-container"
                width="100%"
            >

              <div class="scroll-container">
                <v-list class="scroll-list">
                      <v-list-item
                          v-for="limit in menEvents"
                          :key="limit.idlimit"
                          :title="limit.disciplina.nazivdisciplina + ' ' + limit.disciplina.spol"
                          :subtitle=" 'time: ' + $formatTime(limit.vrijeme)"
                      ></v-list-item>
                  </v-list>
                  <v-list class="scroll-list">
                      <v-list-item
                          v-for="limit in womenEvents"
                          :key="limit.idlimit"
                          :title="limit.disciplina.nazivdisciplina + ' ' + limit.disciplina.spol"
                          :subtitle=" 'time: ' + $formatTime(limit.vrijeme)"
                      ></v-list-item>
                  </v-list>
                </div>
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
import {mapState, mapActions} from 'vuex'


export default {
    data () {
        return {
          dialogm1: '',
          dialog: false,
        }
    },
    created(){
        this.fetchLimiti()
    },
    computed: {
        ...mapState('limiti', ['limiti']),
        menEvents() {
            return this.limiti.filter(item => item.disciplina.spol == 'M')
        },
        womenEvents() {
            return this.limiti.filter(item => item.disciplina.spol == 'F')
        }, 
    },
    methods:{
        ...mapActions('limiti', ['fetchLimiti']),   
          
    }
}
</script>

<style scoped>
.scroll-container {
  overflow: auto;
  max-height: 600px; 
  display: flex;
  justify-content: space-between;
}

.scroll-list {
  flex: 1;
  width: 50%;
  overflow: auto;
}

</style>