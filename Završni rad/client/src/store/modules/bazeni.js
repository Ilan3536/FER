import axios from '@/util/axiosConfig'

const state = {
    pools: [],

}
  
const mutations = {
    setPools(state, pools){
        state.pools = pools
    }

}
  
const actions = {
    fetchPools({ commit }){
        console.log('/api/bazeni')
        axios
        .get('/bazeni')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setPools', response.data);
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
  