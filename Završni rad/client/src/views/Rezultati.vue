<template>
    <Layout>
        <v-container fluid>
            <v-row dense>
              <v-card>
                <h1>Welcome to the Rezultati Page! {{ count }}</h1>
            
                    <button @click="increment">Incremenet Count</button>
                    <button @click="fetchRezultati">Fetch Rezultati</button>
                    <ul>
                        <li v-for="rezultat in rezultati" :key="rezultat.idrezultat">
                            {{rezultat.vrijeme}}
                        </li>
                    </ul>
              </v-card>
            </v-row>
          </v-container>
    </Layout>
</template>
<script>
import {mapState, mapMutations, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'

export default {
    name: 'HomeView',
    components: {
        Layout,
    },
    computed: {
        ...mapState('count', ['count']),
        ...mapState('rezultati', ['rezultati'])
    },
    methods: {
        ...mapMutations('count', ['increment']),
        ...mapActions('rezultati', ['fetchRezultati']),
    }
};
</script>


<style>
    
</style>