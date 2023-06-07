import axios from '@/util/axiosConfig'

const state = {
    competitions: [],
    currentCompetition: [],
    types:[],

}
  
const mutations = {
    setCompetitions(state, competitions){
        state.competitions = competitions
    },
    setCurrentCompetition(state, currentCompetition){
        state.currentCompetition = currentCompetition
    },
    setNewCompetition(state, newCompetition){
        state.competitions.push(newCompetition)
    },
    setTypes(state, types){
        state.types = types
    }


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
    fetchTypes({ commit }){
        console.log('/api/natjecanja/vrste')
        axios
        .get('/natjecanja/vrste')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setTypes', response.data);
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
    addCompetition( { commit }, payload ) {
        console.log('/api/natjecanja' , payload.competitionData)
        axios
          .post('http://localhost:8080/natjecanja', payload.competitionData)
          .then(response => {
            commit('setNewCompetition', response.data)
            console.log('Competition added successfully:', response.data);
          })
          .catch(error => {
            console.error('Error adding competition:', error);
          });
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
  