const state = {
    count: 0
  };
  
  const mutations = {
    increment(state) {
      state.count++;
    }
  };
  
  const actions = {
    incrementCount({ commit }) {
      commit('increment');
    }
  };
  
  const getters = {
    getCount(state) {
      return state.count;
    }
  };
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
  };
  