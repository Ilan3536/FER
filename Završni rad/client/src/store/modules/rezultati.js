import axios from '@/util/axiosConfig'

const state = {
    rezultati: ['idehas'],

}
  
const mutations = {
    setRezultati(state, rezultati){
        state.rezultati = rezultati
    }

}
  
const actions = {
    fetchRezultati({ commit }){
        console.log('/api/rezultati')
        axios
        .get('/rezultati')
        .then(response => {
            console.log("responseData: "  + response)
            commit('setRezultati', response.data);
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
  