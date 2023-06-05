<template>
    <Layout title="Swimmer">
        <v-container class="container-center">
            <EasyDataTable
                :headers="headers"
                :items="mappedRekordi"
                buttons-pagination
            />
        </v-container>
    </Layout>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'

export default {
    name: 'SwimmersPage',
    components: {
        Layout,
        EasyDataTable: window['vue3-easy-data-table'],
    },
    data(){
        return {
            headers: [
                { text: 'Eventid', value: 'eventid', sortable: true },
                { text: 'event', value: 'event', sortable: true },
                { text: 'time', value: 'time', sortable: true },
                { text: 'points', value: 'points', sortable: true },
                { text: 'date', value: 'date', sortable: true },
            ],
            mappedRekordi:[]
        }
    },
    async created(){
        await this.fetchSwimmer({
                ime: this.$route.params.ime,
                prezime: this.$route.params.prezime,
            })
        this.fetchResultsByPerson(this.swimmer.idosoba)
        
    },
    async mounted(){
        await this.fetchSwimmer({
                ime: this.$route.params.ime,
                prezime: this.$route.params.prezime,
            })
        this.fetchResultsByPerson(this.swimmer.idosoba)
        
    },
    computed: {
        ...mapState('osobe', ['swimmer']),
        ...mapState('rezultati', ['rekordi']),
        
    },
    watch: {
        rekordi: {
            handler(rekordi) {
                this.mapRekordi(rekordi)
            },
        },
    },
    methods: {
        ...mapActions('osobe', ['fetchSwimmer']),
        ...mapActions('rezultati', ['fetchResultsByPerson']),
        mapRekordi(rekordi){
            this.mappedRekordi = rekordi.map(item => {
            return {
                eventid: item[0],
                event: item[1],
                time: item[2],
                points: item[3],
                date: item[4],
                person: item[5],
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