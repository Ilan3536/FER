<template>
    <Layout title="Swimming competitions">
        <v-container>
            <v-row>
                <v-col v-for="(competition, index) in competitions" :key="index" cols="12" sm="6" md="4" lg="3">  
                    <div v-if="competition.nazivnatjecanje!=null">
                    <CompetitionCard
                        :competition="competition"
                    >
                    </CompetitionCard>
                </div>
                </v-col>
                <v-col class="plus-container" cols="12" sm="6" md="4" lg="3">
                    <AddCompetition />
                </v-col>
            </v-row>
        </v-container>
    </Layout>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'
import CompetitionCard from './CompetitionCard.vue';
import AddCompetition from './AddCompetition.vue';

export default {
    name: 'Competitions',
    components: {
        Layout,
        CompetitionCard,
        AddCompetition,
    },
   
    created(){
        this.fetchCompetitions()
        
    },
    computed: {
        ...mapState('natjecanja', ['competitions'])
        
    },
    
    methods: {
        ...mapActions('natjecanja', ['fetchCompetitions']),
        
    },
    
};
</script>


<style scoped>
.container-center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.plus-container {
    display: flex;
    justify-content: center;
    align-items: center;
}
    
</style>