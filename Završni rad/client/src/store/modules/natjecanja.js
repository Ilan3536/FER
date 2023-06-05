import axios from '@/util/axiosConfig'

const state = {
    competitions: [],
    currentCompetition: [],

}
  
const mutations = {
    setCompetitions(state, competitions){
        state.competitions = competitions
    },
    setCurrentCompetition(state, currentCompetition){
        state.currentCompetition = currentCompetition
    },


}
  
const actions = {
    fetchCompetitions({ commit }){
        console.log('/api/natjecanja')
        axios
        .get('/natjecanja')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setCompetitions', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    },
    fetchCurrentCompetition({ commit }, id){
        console.log(`/api/natjecanja/${id}`)
        axios
        .get(`http://localhost:8080/natjecanja/${id}`)
        .then(response => {

            console.log("response.data: " + response.data)
            commit('setCurrentCompetition', response.data[0]);
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
  