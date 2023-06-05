import axios from '@/util/axiosConfig'

const state = {
    qualified: [],
    currentCompetition: [],
    eventResults: [],

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

}
  
const actions = {
    /* fetchRezultati({ commit }){
        console.log('/api/rezultati')
        axios
        .get('/rezultati')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setAllResults', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    }, */
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
    /* fetchResultsByCompetition({ commit }, id){
        console.log(`/api/rezultati/natjecanje/${id}`)
        axios
        .get(`http://localhost:8080/rezultati/natjecanje/${id}`)
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setCompetitionResults', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    }, */
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
  