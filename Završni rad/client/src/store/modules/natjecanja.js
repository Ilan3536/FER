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
    addCompetition({payload}) {
        console.log('/api/natjecanja' , payload)
        console.log('/api/natjecanja' , payload.competitionData)
        axios
          .post('http://localhost:8080/natjecanja', payload.competitionData)
          .then(response => {
            console.log('Competition added successfully:', response.data);
            // You can perform additional actions here if needed
          })
          .catch(error => {
            console.error('Error adding competition:', error);
            // Handle the error or show a notification to the user
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
  