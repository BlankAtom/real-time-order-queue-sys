import { createStore } from "vuex";
import { routerMap } from "../router/router";
import { filterAsyncRouter } from "../utils/tool";
import createPersistedState from "vuex-persistedstate";
import SecureLS from "secure-ls";
import {CLEAR_USER, SET_USER, SET_ROUTES, SET_TOKEN, SET_ROLE} from "./mutation-types";
import "./role_types"
import {ADMIN, CUSTOMER, MERCHANT} from "./role_types";
const state = {
    // username
    users: null,
    // 路由导航内容
    routers: [],
    // 登录token
    token: null ,
    // 用户角色, customer, admin, merchant字符串
    role: null

};

const getters = {
    getUserName(state) {
        return !state.users ? "" : state.users.username;
    }
};

const mutations = {
    [CLEAR_USER](state) {
        state.users = null;
        state.routers = [];
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
    setUser({ commit }, payload) {
        let deepCopy = JSON.parse(JSON.stringify(devMap))
        // switch (payload.role) {
        //     case "admin":
        //         deepCopy = JSON.parse(JSON.stringify(layoutMap));
        //         break
        //     case "customer":
        //         deepCopy = JSON.parse(JSON.stringify(layoutMapCustomer));
        //         break
        //     case "merchant":
        //         deepCopy = JSON.parse(JSON.stringify(layoutMapMerchant));
        //         break
        // }

        let accessedRouters = filterAsyncRouter(deepCopy, payload.role);
        commit(SET_USER, payload);
        commit(SET_ROUTES, accessedRouters);
        commit(SET_ROLE, payload.role)

        console.log(payload, deepCopy, accessedRouters)
    },
    setToken({ commit}, data ) {
        // 根据传入的角色分配路由数组
        let {token, username, role} = data
        let lmap = null
        if( role === ADMIN ){
            lmap = layoutMap
        } else if (role === MERCHANT) {
            lmap = layoutMapMerchant
        } else {
            lmap = layoutMapCustomer
        }
        // 解析路由数组
        let accessedRouters = filterAsyncRouter(JSON.parse(JSON.stringify(lmap)), role)
        // 调用set设置四个参数
        commit(SET_TOKEN, token)
        commit(SET_USER, username);
        commit(SET_ROUTES, accessedRouters);
        commit(SET_ROLE, role)
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
    state,
    getters,
    mutations,
    actions
    // plugins: [myPersistedState]
});
