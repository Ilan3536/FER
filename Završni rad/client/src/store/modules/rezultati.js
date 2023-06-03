import axios from '@/util/axiosConfig'

const state = {
    rezultati: [],
    qualified: [],

}
  
const mutations = {
    setRezultati(state, rezultati){
        state.rezultati = rezultati
    },
    setQualified(state, qualified){
        state.qualified = qualified
    }

}
  
const actions = {
    fetchRezultati({ commit }){
        console.log('/api/rezultati')
        axios
        .get('/rezultati')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setRezultati', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    },
    fetchQualified({ commit }){
        console.log('/api/rezultati/kvalificirani')
        axios
        .get('/rezultati/kvalificirani')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setQualified', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    }
}
  
const getters = {
}
  
export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
  