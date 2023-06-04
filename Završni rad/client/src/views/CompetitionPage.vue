<template>
    <Layout title="Competition pages">
        <v-container>
            <div>
                <h1>{{ currentCompetition.nazivnatjecanje }}</h1>
                <h4> {{ "Dates: " + currentCompetition.datumod + " - " + currentCompetition.datumdo}}</h4>
                <div class="text-subtitle-2 mb-2">Men</div>
                <v-expansion-panels>
                    <v-expansion-panel
                        v-for="result in menResults"
                        :key="result.idrezultat"
                        :title="result.disciplina.nazivdisciplina"
                    >
                        <v-expansion-panel-text>
                            <EventResults 
                                :idnatjecanje="currentCompetition.idnatjecanje"
                                :iddisciplina="result.disciplina.iddisciplina"
                            ></EventResults>
                        </v-expansion-panel-text>
                    </v-expansion-panel>
                </v-expansion-panels>

                <div class="text-subtitle-2 mb-2">Women</div>
                <v-expansion-panels>
                    <v-expansion-panel
                        v-for="result in womenResults"
                        :key="result.idrezultat"
                        :title="result.disciplina.nazivdisciplina"
                    >
                        <v-expansion-panel-text>
                            <EventResults 
                                :idnatjecanje="currentCompetition.idnatjecanje"
                                :iddisciplina="result.disciplina.iddisciplina"
                            ></EventResults>
                        </v-expansion-panel-text>
                    </v-expansion-panel>
                </v-expansion-panels>
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
    created(){
        this.fetchCurrentCompetition(this.$route.params.id)
        this.fetchResultsByCompetition(this.$route.params.id)
        
    },
    computed: {
        ...mapState('natjecanja', ['currentCompetition']),
        ...mapState('rezultati', ['competitionResults']),
        menResults() {
            return this.competitionResults.filter(item => item.osoba.spol == 'M')
        },
        womenResults() {
            return this.competitionResults.filter(item => item.osoba.spol == 'F')
        },
    },
    methods: {
        ...mapActions('natjecanja', ['fetchCurrentCompetition']),
        ...mapActions('rezultati', ['fetchResultsByCompetition']),
    }
}
</script>
<style>
.text-subtitle-2 {
    margin: 10px;
    display: flex;
    justify-content: center;
    align-items: center; 
}
</style>