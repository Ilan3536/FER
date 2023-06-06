<template>
    <form @submit="submit">
      <v-text-field
        v-model="nazivnatjecanje.value.value"
        :counter="30"
        :error-messages="nazivnatjecanje.errorMessage.value"
        label="Competition Name"
      ></v-text-field>

      <v-text-field
        v-model="datumod.value.value"
        label="Date from"
        :error-messages="datumod.errorMessage.value"
      ></v-text-field>

      <v-text-field
        v-model="datumdo.value.value"
        label="Date to"
        :error-messages="datumdo.errorMessage.value"
      ></v-text-field>
      
      <v-select
            v-model="bazen.value.value"
            :items="pools"
            item-title="nazivbazen"
            item-value="idbazen"
            label="Select pool"
            persistent-hint
            return-object
            single-line
            :error-messages="bazen.errorMessage.value"
        ></v-select>
  
        <!-- <v-radio-group v-model="radiobox.value.value" inline>
            <v-radio 
                v-for="item in items"
                :key="item.id"
                :label="item.name"
                :value="item"
                :error-messages="radiobox.errorMessage.value"
            >
            </v-radio>
        </v-radio-group> -->
  
      <v-btn class="me-4" type="submit"> submit </v-btn>
  
      <v-btn @click="handleReset"> clear </v-btn>
    </form>
  </template>
  
  <script>
    import { ref } from 'vue'
    import { useField, useForm } from 'vee-validate'
    import { mapActions, mapState, useStore } from 'vuex'
  
    export default {
      setup() {
        const { handleSubmit, handleReset } = useForm({
          validationSchema: {
            nazivnatjecanje(value) {
              if (value?.length >= 2) return true
  
              return 'Name needs to be at least 2 characters.'
            },
            datumod(value) {
                if (value) return true
  
                return 'Select an item.'
            },
            datumdo(value) {
                if (value) return true
  
                return 'Select an item.'
            },
            bazen(value) {
              if (value) return true
  
              return 'Select an item.'
            },
            /* vrsta(value) {
              if (value) return true
  
              return 'Must be selected.'
            }, */
          },
        })
        const nazivnatjecanje = useField('nazivnatjecanje')
        const datumod = useField('datumod')
        const datumdo = useField('datumdo')
        const bazen = useField('bazen')
        //const radiobox = useField('radiobox')
  
        const items = ref([{
            id: 1,
            name: 'olympic games',
            checked: false,
        }, 
        {
            id: 2,
            name: 'world championships',
            checked: false,
        },
        {
            id: 3,
            name: 'international swim meet',
            checked: false,
        },
        {
            id: 4,
            name: 'national swim meet',
            checked: false,
        }
        ])
   
        const store = useStore();

        const submit = handleSubmit(values => {
            console.log(values)
            store.dispatch('natjecanja/addCompetition', {
                competitionData: values
            })
        })
  
        return {
          nazivnatjecanje,
          datumdo,
          datumod,
          bazen,
          //radiobox,
          items,
          submit,
          handleReset,
        }
      },
      created() {
        this.fetchPools()
      },
      computed: {
        ...mapState('bazeni', ['pools'])
      },
      methods: {
        ...mapActions('bazeni', ['fetchPools']),
      }
    }
  </script>
  