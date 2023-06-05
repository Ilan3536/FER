import axios from '@/util/axiosConfig'

const state = {
    qualified: [],
    currentCompetition: [],
    eventResults: [],
    rekordi: [],

}
  
const mutations = {
    setQualified(state, qualified){
        state.qualified = qualified
    },
    setCurrentCompetition(state, competition){
        state.currentCompetition = competition
    },
    setEventResults(state, eventResults){
        state.eventResults = eventResults
    },
    setSwimmerResults(state, swimmerResults){
        state.rekordi = swimmerResults
    },

}
  
const actions = {
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
    },
    fetchResultsByCompetitionAndEvent({ commit }, payload){
        const {idn, idd } = payload
        console.log(`/api/rezultati/natjecanje/${idn}/disciplina/${idd}`)
        axios
        .get(`http://localhost:8080/rezultati/natjecanje/${idn}/disciplina/${idd}`)
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setEventResults', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    },
    fetchResultsByPerson({ commit }, id){
        console.log(`/api/rezultati/osoba/${id}`)
        axios
        .get(`http://localhost:8080/rezultati/osoba/${id}`)
        .then(response => {

            console.log("response.data: " + response.data)
            commit('setSwimmerResults', response.data);
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
  