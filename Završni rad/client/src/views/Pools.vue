<template>
    <Layout title="Olympic swimming pools">
        <v-container>
            <v-row>
                <v-col v-for="(pool, index) in pools" :key="index" cols="12" sm="6" md="4" lg="3">  
                    <PoolCard
                    :pool="pool"
                    >
                    </PoolCard>
                </v-col>
            </v-row>
        </v-container>
    </Layout>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'
import PoolCard from './PoolCard.vue';

export default {
    name: 'Pools',
    components: {
        Layout,
        PoolCard,
    },

    created(){
        this.fetchPools()
        
    },
    computed: {
        ...mapState('bazeni', ['pools'])
        
    },

    methods: {
        ...mapActions('bazeni', ['fetchPools']),
    },
    
};
</script>


<style scoped>
.container-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
    
</style>