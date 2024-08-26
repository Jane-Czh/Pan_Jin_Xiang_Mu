

const state = {
  exportedData: [],
};

const mutations = {
  setExportedData(state, data) {
    state.exportedData = data;
  },
};

const actions = {
  updateExportedData({ commit }, data) {
    commit('setExportedData', data);
  },
};

const getters = {
  getExportedData: (state) => state.exportedData,
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
};


