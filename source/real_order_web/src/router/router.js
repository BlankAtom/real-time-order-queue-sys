import Layout from "../layout/Index.vue";

const routerMap = [
    {
        path: "",
        name: "Customer",
        meta: {
            title: "选择商家",
            icon: "el-icon-location",
            roles: ["customer"]
        },
        component: ()=> import("../views/custom/index.vue"),
    },
    {
        path: "queue/:mId",
        name: "Queue",
        meta: {
            title: "队列",
            icon: "el-icon-s-comment",
            roles: ["merc314hant"]
        },
        component: ()=>import("../views/custom/Queue.vue")
    },
    {
        path: "myqueue",
        name: "MyQueue",
        meta: {
            title: "我的队列",
            icon: "el-icon-s-comment",
            roles: ["customer"]
        },
        component: ()=>import("../views/custom/MyQueue.vue")
    },
    {
        path: "",
        name: "MerchantMain",
        meta: { title: "叫号点单", icon: "el-icon-phone", roles: ["merchant"] },
        component: () => import("../views/merchant/Calling.vue")
    },
    {
        path: "order",
        name: "Order",
        meta: { title: "点单", icon: "el-icon-document", roles: ["merchant"]},
        component: () => import("../views/merchant/Order.vue")
    },
    {
        path: "dish",
        name: "Player",
        meta: { title: "查看菜品", icon: "el-icon-video-camera-solid" },
        component: () => import("../views/merchant/Dish.vue")
    },
    {
        path: "BigData1",
        name: "BigData1",
        meta: { title: "大数据1", icon: "el-icon-document", roles: ["admin"]},
        component: () => import("../views/admin/BigData1.vue")
    },
    {
        path: "BigData2",
        name: "BigData2",
        meta: { title: "大数据2", icon: "el-icon-document", roles: ["admin"]},
        component: () => import("../views/admin/BigData2.vue")
    },
    {
        path: "BigData3",
        name: "BigData3",
        meta: { title: "大数据3", icon: "el-icon-document", roles: ["admin"]},
        component: () => import("../views/admin/BigData3.vue")
    },
    {
        path: "List",
        name: "List",
        meta: { title: "查看订单", icon: "el-icon-video-camera-solid", roles: ["merchant"] },
        component: () => import("../views/data/List.vue")
    },
    {
        path: "role",
        name: "AdminRole",
        meta: { title: "角色列表", icon: "el-icon-s-custom", roles: ["admin"] },
        component: () => import("../views/admin/RoleList.vue")
    },
    {
        path: "",
        name: "AdminMerchant",
        meta: { title: "商家列表", icon: "el-icon-s-custom", roles: ["admin"] },
        component: () => import("../views/admin/Merchant.vue")
    }
]


const routes = [
    { path: "/login", name: "Login", meta: { title: "登录" }, component: () => import("../views/login/Login.vue") },
    { path: "/", name: "Layout", meta: {title: ""},component: Layout, children: [...routerMap] },

];

export { routes, routerMap};
