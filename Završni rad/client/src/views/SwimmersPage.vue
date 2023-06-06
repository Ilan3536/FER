<template>
    <Layout :title="swimmer[0].idosoba + ' ' + swimmer[0].imeosoba + ' ' +swimmer[0].prezimeosoba">
        <div v-if="swimmer.length > 0">
            <h2 class="centered">
                {{ swimmer[0].klub.nazivklub + ", " + swimmer[0].drzava.nazivdrzava}}
            </h2>
            <h4 class="centered"> 
                {{ "Date of birth: " + $formatDate(swimmer[0].datumrodjenja) + 
                ",  height: " + swimmer[0].visina + "cm,  weight: " + swimmer[0].tezina + "kg"  }}
                </h4>
            <v-container class="container-center">
                <EasyDataTable
                    :headers="headers"
                    :items="mappedRekordi"
                    buttons-pagination
                />
            </v-container>
        </div>
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
    created(){
        this.fetchSwimmer({
                ime: this.$route.params.ime,
                prezime: this.$route.params.prezime,
            })        
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
        swimmer: {
            handler(swimmer) {
                this.fetchResultsByPerson(swimmer[0].idosoba)
            }
        }
    },
    methods: {
        ...mapActions('osobe', ['fetchSwimmer']),
        ...mapActions('rezultati', ['fetchResultsByPerson']),
        mapRekordi(rekordi){
            this.mappedRekordi = rekordi.map(item => {
            return {
                eventid: item[0],
                event: item[1],
                time: this.$formatTime(item[2]),
                points: item[3],
                date: this.$formatDate(item[4]),
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
.centered {
    display: flex;
    align-items: center;
    justify-content: center;
}
    
</style>