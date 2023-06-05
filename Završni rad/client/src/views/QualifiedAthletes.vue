<template>
    <Layout title="Athletes qualified for National Championship 2023">
        <v-container class="container-center">
            <EasyDataTable
                :headers="headers"
                :items="mappedQualified"
                buttons-pagination
            />
        </v-container>
        <Limiti class="limiti-button">

        </Limiti>
    </Layout>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import Layout from '@/components/Layout/Layout.vue'
import Limiti from '@/views/Limiti.vue';

export default {
    name: 'QualifiedAthletes',
    components: {
        Layout,
        Limiti,
        EasyDataTable: window['vue3-easy-data-table'],
    },
    data(){
        return {
            headers: [
                { text: 'Id', value: 'id', sortable: true },
                { text: 'Name', value: 'name', sortable: true },
                { text: 'Surname', value: 'surname', sortable: true },
                { text: 'Club', value: 'club', sortable: true, width: 160 },
                { text: 'Events', value: 'events', width: 600 }
            ],
            mappedQualified:[]
        }
    },
    created(){
        this.fetchQualified()
        this.fetchLimiti()
        
    },
    computed: {
        ...mapState('rezultati', ['qualified']),
        ...mapState('limiti', ['limiti']),
        
    },
    watch: {
        qualified: {
            handler(qualified) {
                this.mapQualified(qualified)
            },
        },
    },
    methods: {
        ...mapActions('rezultati', ['fetchQualified']),
        ...mapActions('limiti', ['fetchLimiti']),
        mapQualified(qualified){
            this.mappedQualified = qualified.map(item => {
            return {
                id: item[0],
                name: item[1],
                surname: item[2],
                club: item[3],
                events: item[4],
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

.limiti-button {
    margin-top: 70px;
}
    
</style>