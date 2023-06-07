<template>
    <form @submit.prevent="handleSubmit">
      <v-text-field
        v-model="nazivnatjecanje"
        :counter="30"
        label="Competition Name"
        :error-messages="getErrorMessage('nazivnatjecanje')"
      ></v-text-field>

      <v-text-field
        v-model="datumod"
        label="Date from"
        type="date"
        :error-messages="getErrorMessage('datumod')"

      ></v-text-field>

      <v-text-field
        v-model="datumdo"
        label="Date to"
        type="date"
        :error-messages="getErrorMessage('datumdo')"

      ></v-text-field>
      
      <v-select
            v-model="bazen"
            :items="pools"
            item-title="nazivbazen"
            item-value="idbazen"
            label="Select pool"
            persistent-hint
            return-object
            single-line
            :error-messages="getErrorMessage('bazen')"
        ></v-select>
  
        <v-radio-group v-model="vrstanatjecanje" inline>
            <v-radio
                v-for="type in types"
                :key="type"
                :label="type"
                :value="type"
            >
            </v-radio>
        </v-radio-group> 
  
      <v-btn class="me-4" type="submit"> submit </v-btn>
  
      <v-btn @click="handleReset"> clear </v-btn>
    </form>
  </template>
  
<script>
import { mapActions, mapState } from 'vuex'

export default {
    name: 'AddFrom',
    data() {
        return {
            nazivnatjecanje: '',
            datumod: '',
            datumdo:'',
            bazen:'Select pool',
            vrstanatjecanje:'',
            formErrors: {},
        }
    },
    created() {
        this.fetchPools()
        this.fetchTypes()
    },
    computed: {
        ...mapState('bazeni', ['pools']),
        ...mapState('natjecanja', ['types'])
    },
    methods: {
        ...mapActions('bazeni', ['fetchPools']),
        ...mapActions('natjecanja', ['fetchTypes', 'addCompetition']),
        handleSubmit(){

            if (!this.validateForm()) {
                return
            } 
            const competitionData = {
                nazivnatjecanje: this.nazivnatjecanje,
                vrstanatjecanje: this.vrstanatjecanje,
                datumod: this.datumod,
                datumdo: this.datumdo,
                bazen: this.bazen,

            }

            this.addCompetition({
                competitionData: competitionData
            })  

        },
        handleReset(){
            this.nazivnatjecanje = ''
            this.datumod = ''
            this.datumdo = ''
            this.bazen = 'Select pool',
            this.vrstanatjecanje = ''
        },
        validateForm() {
            let isValid = true
            this.formErrors = {}

            if (!this.nazivnatjecanje) {
                this.setErrorMessage('nazivnatjecanje', 'Competition Name is required')
                isValid = false
            } else if (this.nazivnatjecanje.length < 2) {
                this.setErrorMessage('nazivnatjecanje', 'Competition Name must be at least 2 characters')
                isValid = false
            }

            if (!this.datumod) {
                this.setErrorMessage('datumod', 'Date from is required')
                isValid = false
            }

            if (!this.datumdo) {
                this.setErrorMessage('datumdo', 'Date to is required')
                isValid = false
            }

            const datefrom = new Date(this.datumod)
            const dateto = new Date(this.datumdo)

            if (datefrom > dateto){
                this.setErrorMessage('datumdo', 'Date to has to be after Date From')
                isValid = false
            }

            if (!this.bazen || this.bazen == 'Select pool') {
                this.setErrorMessage('bazen', 'Pool has to be selected')
                isValid = false
            }

            return isValid

        },
        getErrorMessage(fieldName) {
            return this.formErrors[fieldName] || []
        },
        setErrorMessage(fieldName, message) {
            if (!this.formErrors[fieldName]) {
                this.formErrors[fieldName] = []
            }

        this.formErrors[fieldName].push(message)
        },
    },
}
</script>
  