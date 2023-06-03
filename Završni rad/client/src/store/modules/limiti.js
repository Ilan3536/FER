import axios from '@/util/axiosConfig'

const state = {
    limiti: [],

}
  
const mutations = {
    setLimiti(state, limiti){
        state.limiti = limiti
    }

}
  
const actions = {
    fetchLimiti({ commit }){
        console.log('/api/limiti')
        axios
        .get('/limiti')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setLimiti', response.data);
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
  