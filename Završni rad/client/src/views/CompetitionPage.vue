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
        }
    },
    created(){
        this.fetchCurrentCompetition(this.$route.params.id)
        this.fetchResultsByCompetition(this.$route.params.id)
        this.fetchDistinctEvents(this.$route.params.id)
        
    },
    computed: {
        ...mapState('natjecanja', ['currentCompetition', 'distinctEvents']),
        ...mapState('rezultati', ['competitionResults']),
        menEvents() {
            return this.distinctEvents.filter(item => item.spol == 'M')
        },
        womenEvents() {
            return this.distinctEvents.filter(item => item.spol == 'F')
        },
    },
    watch: {
        '$route.params.id': {
            immediate: true,
            handler(newId){
                this.idnatjecanje = newId
            }
        }
    },
    methods: {
        ...mapActions('natjecanja', ['fetchCurrentCompetition', 'fetchDistinctEvents']),
        ...mapActions('rezultati', ['fetchResultsByCompetition']),  
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