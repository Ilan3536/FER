<template>
    <Layout>
        <v-container class="list-container">
            <v-sheet width="300" class="mx-auto">
                <v-form @submit.prevent="submitForm">
                <v-text-field
                    v-model="name"
                    label="Swimmers First and Last Name"
                ></v-text-field>
                <v-btn type="submit" block class="mt-2">Search</v-btn>
                </v-form>
            </v-sheet>
        </v-container>
    </Layout>
</template>
<script>
import Layout from '@/components/Layout/Layout.vue';
import router from '@/router';
import { mapActions, mapState } from 'vuex';
    
export default {
    components: {
        Layout,
    },
    data(){
        return {
            name:'',
        }
    },
    computed: {
        ...mapState('osobe', ['osobe', 'swimmer']),
    },
    methods: {
        ...mapActions('osobe', ['fetchSwimmer']),
        submitForm(){
            console.log(this.name)
            const [ ime, prezime ] = this.name.split(' ');

            if (ime == null || prezime == null) return

            this.fetchSwimmer({
                ime: ime,
                prezime: prezime,
            })

            if (this.swimmer.length != 0 ){
                router.push(`/swimmers/${ime}/${prezime}`)
            }


        }
    }
};
</script>
<style>
  .dx-list-item-content > div {
    padding: 5px;
    font-size: 15px;
  }
  
  .options {
    margin-top: 20px;
    padding: 20px;
    background: rgba(191, 191, 191, 0.15);
  }
  
  .options .caption {
    font-size: 18px;
    font-weight: 500;
  }
  
  .option {
    margin-top: 10px;
  }
  
  .option > span {
    margin-right: 10px;
  }
  
  .option > .dx-selectbox {
    display: inline-block;
    vertical-align: middle;
    max-width: 350px;
    width: 100%;
  }
  
</style>
  