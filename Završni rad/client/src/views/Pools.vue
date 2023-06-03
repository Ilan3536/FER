<template>
    <Layout title="Olympic swimming pools">
        <v-container>
            <v-row>
                <v-col v-for="(pool, index) in mappedPools" :key="index" cols="12" sm="6" md="4" lg="3">  
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
    data(){
        return {
            mappedPools:[],
        }
    },
    created(){
        this.fetchPools()
        
    },
    computed: {
        ...mapState('bazeni', ['pools'])
        
    },
    watch: {
        pools: {
            handler(pools) {
                this.mapPools(pools)
            },
        },
    },
    methods: {
        ...mapActions('bazeni', ['fetchPools']),
        mapPools(pools){
            this.mappedPools = pools.map(item => {
            return {
                id: item[0],
                name: item[1],
                capacity: item[2],
                country: item[3],
                city: item[4],
                address: item[5],
            }
        })
        }
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