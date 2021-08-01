import { createStore } from "vuex";
import { routerMap } from "../router/router";
import { filterAsyncRouter } from "../utils/tool";
import createPersistedState from "vuex-persistedstate";
import SecureLS from "secure-ls";
import {CLEAR_USER, SET_USER, SET_ROUTES, SET_TOKEN, SET_ROLE} from "./mutation-types";
import "./role_types"




const state = {
    // username
    users: null,
    // 路由导航内容
    routers: null,
    // 登录token
    token: null ,
    // 用户角色, customer, admin, merchant字符串
    role: null

};

const getters = {
    getUserName(state) {
        return !state.users ? "" : state.users;
    },
    getRole(state) {
        return !state.role ? "" : state.role
    }
};

const mutations = {
    [CLEAR_USER](state) {
        state.users = null;
        state.routers = [];
        state.role = null
        state.token = null
    },
    [SET_USER](state, value) {
        state.users = value;
    },
    [SET_ROUTES](state, value) {
        state.routers = value;
    },
    [SET_TOKEN](state, value) {
        state.token = value
    },
    [SET_ROLE](state, value) {
        state.role = value
    }
};

const ls = new SecureLS({
    encodingType: "aes" /* 加密方式 */,
    isCompression: false /* 压缩数据 */,
    encryptionSecret: "vite-vue" /* 加密密钥 */
});

const actions = {
    clearUser({ commit }) {
        commit(CLEAR_USER);
    },
    setUser({ commit }, data) {
        let deepCopy = JSON.parse(JSON.stringify(routerMap))

        let accessedRouters = filterAsyncRouter(deepCopy, data.auth);
        commit(SET_USER, data);
        commit(SET_ROUTES, accessedRouters);
        commit(SET_ROLE, data.auth)
    },
    setToken({ commit}, data ) {
        // 根据传入的角色分配路由数组
        let {token, uname, auth} = data
        // 解析路由数组
        let accessedRouters = filterAsyncRouter(JSON.parse(JSON.stringify(routerMap)), auth)
        // 调用set设置四个参数
        commit(SET_ROUTES, accessedRouters);
        commit(SET_USER, uname);
        commit(SET_TOKEN, token)
        commit(SET_ROLE, auth)
    }
};

const myPersistedState = createPersistedState({
    key: "store",
    storage: window.sessionStorage,
    // storage: {
    //     getItem: state => ls.get(state),
    //     setItem: (state, value) => ls.set(state, value),
    //     removeItem: state => ls.remove(state)
    // } /* 永久存储 */
    reducer(state) {
        return { ...state };
    }
});

export default createStore({
    state: sessionStorage.getItem("state") ? JSON.parse(sessionStorage.getItem("state")) : state,
    getters,
    mutations,
    actions
    // plugins: [myPersistedState]
});
