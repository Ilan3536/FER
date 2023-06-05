import axios from '@/util/axiosConfig'

const state = {
    distinctEvents: [],

}
  
const mutations = {
    setDistinctEvents(state, distinctEvents){
        state.distinctEvents = distinctEvents
    }

}
  
const actions = {
    fetchDistinctEvents({ commit }, id){
        console.log(`/api/discipline/groupedByNatjecanje/${id}`)
        axios
        .get(`http://localhost:8080/discipline/groupedByNatjecanje/${id}`)
        .then(response => {

            console.log("response.data: " + response.data)
            commit('setDistinctEvents', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    },
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
  