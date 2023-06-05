<template>
    <Layout title="World Records">
        <v-container class="container-center">
            <EasyDataTable
                :headers="headers"
                :items="mappedRekordi"
                buttons-pagination
                rows-per-page=17
            />
        </v-container>
    </Layout>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'

export default {
    name: 'WorldRecords',
    components: {
        Layout,
        EasyDataTable: window['vue3-easy-data-table'],
    },
    data(){
        return {
            headers: [
                { text: 'Name', value: 'name', sortable: true },
                { text: 'Surname', value: 'surname', sortable: true },
                { text: 'Country', value: 'country', sortable: true },
                { text: 'Event', value: 'event', sortable: true },
                { text: 'Gender', value: 'gender', sortable: true },
                { text: 'Time', value: 'time', sortable: true },
                { text: 'Meet', value: 'meet', sortable: true },
            ],
            mappedRekordi:[]
        }
    },
    created(){
        this.fetchRekordi()
        
    },
    computed: {
        ...mapState('rekordi', ['rekordi'])
        
    },
    watch: {
        rekordi: {
            handler(rekordi) {
                this.mapRekordi(rekordi)
            },
        },
    },
    methods: {
        ...mapActions('rekordi', ['fetchRekordi']),
        mapRekordi(rekordi){
            this.mappedRekordi = rekordi.map(item => {
            return {
                name: item[0],
                surname: item[1],
                event: item[2],
                gender: item[3],
                time: item[4],
                meet: item[5],
                country: item[6]
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