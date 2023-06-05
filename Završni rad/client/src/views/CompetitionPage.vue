<template>
    <Layout title="Competition pages">
        <v-container>
            <h1 class="centered">{{ currentCompetition.nazivnatjecanje }}</h1>
            <h4 class="centered"> {{ "Dates: " + currentCompetition.datumod + " - " + currentCompetition.datumdo}}</h4>
            <div class="container">
                <div class="column">
                    <div class="text">Men</div>
                    <v-expansion-panels>
                        <v-expansion-panel
                            v-for="event in menEvents"
                            :key="event.iddisciplina"
                            :title="event.nazivdisciplina"
                        >
                            <v-expansion-panel-text>
                                <EventResults 
                                    :idnatjecanje="this.idnatjecanje"
                                    :iddisciplina="event.iddisciplina"
                                    :vrstanatjecanje="currentCompetition.vrstanatjecanje"
                                ></EventResults>
                            </v-expansion-panel-text>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </div>
                <div class="column">
                    <div class="text mb-2">Women</div>
                        <v-expansion-panels>
                            <v-expansion-panel
                                v-for="event in womenEvents"
                                :key="event.iddisciplina"
                                :title="event.nazivdisciplina"
                            >
                                <v-expansion-panel-text>
                                    <EventResults 
                                        :idnatjecanje="this.idnatjecanje"
                                        :iddisciplina="event.iddisciplina"
                                        :vrstanatjecanje="currentCompetition.vrstanatjecanje"
                                    ></EventResults>
                                </v-expansion-panel-text>
                            </v-expansion-panel>
                        </v-expansion-panels>
                    </div>
                </div>
        </v-container>
    </Layout>
</template>
<script>
import Layout from '@/components/Layout/Layout.vue';
import { mapActions, mapState } from 'vuex';
import EventResults from './EventResults.vue'

export default {
    name: "CompetitionPage",
    components: { 
        Layout,
        EventResults,
    },
    data() {
        return {
            idnatjecanje: null,
            mappedDistinctEvents: [],
        }
    },
    created(){
        this.fetchCurrentCompetition(this.$route.params.id)
        this.fetchDistinctEvents(this.$route.params.id)
    },
    computed: {
        ...mapState('natjecanja', ['currentCompetition']),
        ...mapState('discipline', ['distinctEvents']),
        menEvents() {
            return this.mappedDistinctEvents.filter(item => item.spol == 'M')
        },
        womenEvents() {
            return this.mappedDistinctEvents.filter(item => item.spol == 'F')
        },
    },
    watch: {
        '$route.params.id': {
            immediate: true,
            handler(newId){
                this.idnatjecanje = newId
            }
        },
        distinctEvents : {
            handler(distinctEvents) {
                this.mapDistinctEvents(distinctEvents)
            }
        }
    },
    methods: {
        ...mapActions('natjecanja', ['fetchCurrentCompetition']),
        ...mapActions('discipline', ['fetchDistinctEvents']),  
        mapDistinctEvents(distinctEvents){
            this.mappedDistinctEvents = distinctEvents.map(item =>{
                return {
                    iddisciplina: item[0],
                    nazivdisciplina: item[1],
                    spol: item[2],
                }
            })
        }
    }
}
</script>
<style>
.text {
    margin: 10px;
    display: flex;
    justify-content: center;
    align-items: center; 
    font-size: 20px;
}

.container {
    display: flex;
}
.column {
    flex: 1;
    margin-right: 20px;
}

.centered {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>