import axios from '@/util/axiosConfig'

const state = {
    osobe: [],
    swimmer: [],

}
  
const mutations = {
    setOsobe(state, osobe){
        state.osobe = osobe
    },
    setSwimmer(state, swimmer){
        state.swimmer = swimmer
    }

}
  
const actions = {
    fetchOsobe({ commit }){
        console.log('/api/osobe')
        axios
        .get('/osobe')
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setOsobe', response.data);
        })
        .catch(error => {
            console.error(error)
        })
    },
    fetchSwimmer({ commit }, payload){
        const { ime, prezime } = payload
        console.log(`/api/osobe/${ime}/${prezime}`)
        axios
        .get(`http://localhost:8080/osobe/${ime}/${prezime}`)
        .then(response => {
            console.log("responseData: "  + response.data)
            commit('setSwimmer', response.data);
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
  