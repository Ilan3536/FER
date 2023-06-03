import axios from '@/util/axiosConfig'

const state = {
    rekordi: [],

}
  
const mutations = {
    setRekordi(state, rekordi){
        state.rekordi = rekordi
    }

}
  
const actions = {
    fetchRekordi({ commit }){
        console.log('/api/rekordi')
        axios
        .get('/rekordi')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setRekordi', response.data);
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
  