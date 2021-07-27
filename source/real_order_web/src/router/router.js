import Layout from "../layout/Index.vue";
import RouteView from "../components/RouteView.vue";
import Queue from "../views/common/Queue.vue";



const devMap = [
    {
        path: "hxq",
        name: "HXQ",
        meta: {
            title: "HXQ"
        },
        component: RouteView,
        children: [
            {
                path: "",
                name: "Customer",
                meta: {
                    title: "顾客-选择商家",
                    icon: "el-icon-location"
                },
                component: ()=> import("../views/custom/index.vue"),
            },
            {
                path: "customerSelf",
                name: "CustomerSelf",
                meta: { title: "介个先当做用户", icon: "el-icon-s-comment" },
                component: () => import("../views/common/ChooseMerchant.vue")
            },

            {
                path: "queue/:mId",
                name: "Queue",
                meta: {title: "队列", icon: "el-icon-s-comment"},
                component: ()=>import("../views/common/Queue.vue")
            }
        ]
    },
    {
        path: "chb",
        name: "CHB",
        meta: {
            title: "CHB"
        },
        component: RouteView,
        children: [
            {
                path: "",
                name: "MerchantMain",
                meta: { title: "叫号点单", icon: "el-icon-phone" },
                component: () => import("../views/common/Calling.vue")
            },
            {
                path: "order",
                name: "Order",
                meta: { title: "点单", icon: "el-icon-document"},
                component: () => import("../views/common/Order.vue")
            },
            {
                path: "BigData1",
                name: "BigData1",
                meta: { title: "大数据1", icon: "el-icon-document"},
                component: () => import("../views/admin/BigData1.vue")
            },
            {
                path: "BigData2",
                name: "BigData2",
                meta: { title: "大数据2", icon: "el-icon-document"},
                component: () => import("../views/admin/BigData2.vue")
            },
            {
                path: "BigData3",
                name: "BigData3",
                meta: { title: "大数据3", icon: "el-icon-document"},
                component: () => import("../views/admin/BigData3.vue")
            },
        ]
    },
    {
        path: "hzr",
        name: "HZR",
        meta: {
            title: "HZR"
        },
        component: RouteView,
        children: [

            {
                path: "",
                name: "Player",
                meta: { title: "查看菜品", icon: "el-icon-video-camera-solid" },
                component: () => import("../views/common/XGPlayer.vue")
            },
        ]
    },
    {
        path: "wds",
        name: "WDS",
        meta: {
            title: "WDS"

        },
        component: RouteView,
        children: [
            {
                path: "List",
                name: "List",
                meta: { title: "查看订单", icon: "el-icon-video-camera-solid" },
                component: () => import("../views/data/List.vue")
            },
            {
                path: "role",
                name: "AdminRole",
                meta: { title: "角色列表", icon: "el-icon-s-custom" },
                component: () => import("../views/admin/RoleList.vue")
            }
        ]

    }
]
// 普通用户导航
// [个人信息、商家]
const layoutMapCustomer = [
    {
        path: "customer",
        name: "Customer",
        meta: {
            title: "顾客-选择商家",
            icon: "el-icon-location"
        },
        component: ()=> import("../views/custom/index.vue"),
    },

    {
        path: "customerSelf",
        name: "CustomerSelf",
        meta: { title: "介个先当做用户", icon: "el-icon-s-comment" },
        component: () => import("../views/common/ChooseMerchant.vue")
    },

    {
        path: "queue/:mId",
        name: "Queue",
        meta: {title: "队列", icon: "el-icon-s-comment"},
        component: ()=>import("../views/common/Queue.vue")
    }
]

// 商家用户导航
// [个人信息、商家]
const layoutMapMerchant = [

    {
        path: "merchant",
        name: "MerchantMain",
        meta: { title: "叫号点单", icon: "el-icon-phone" , roles: ["merchant"],},
        component: () => import("../views/common/Calling.vue")
    },
    {
        path: "order",
        name: "Order",
        meta: { title: "点单", icon: "el-icon-document" , roles: ["merchant"],},
        component: () => import("../views/common/Order.vue")
    },
]
// 管理员导航
const layoutMap = [
    {
        path: "",
        name: "Index",
        meta: { title: "控制台", icon: "el-icon-s-home", roles: ["admin"], },
        component: () => import("../views/Index.vue")
    },
    {
        path: "data",
        name: "Data",
        component: RouteView,
        meta: { title: "数据管理", icon: "el-icon-s-help" , roles: ["admin"],},
        children: [
            {
                path: "",
                name: "DataList",
                meta: { title: "数据列表" ,roles: ["admin"], },
                component: () => import("../views/data/List.vue")
            },
            {
                path: "table",
                name: "DataTable",
                meta: { title: "数据表格", roles: ["admin"] },
                component: () => import("../views/data/Table.vue")
            }
        ]
    },
    {
        path: "admin",
        name: "Admin",
        meta: { title: "用户管理", icon: "el-icon-s-tools", roles: ["admin"] },
        component: RouteView,
        children: [
            {
                path: "",
                name: "AdminAuth",
                meta: { title: "用户列表" },
                component: () => import("../views/admin/AuthList.vue")
            },
            {
                path: "role",
                name: "AdminRole",
                meta: { title: "角色列表" },
                component: () => import("../views/admin/RoleList.vue")
            }
        ]
    },
    {
        path: "player",
        name: "Player",
        meta: { title: "查看菜品", icon: "el-icon-vid" },
        component: () => import("../views/common/XGPlayer.vue")
    },


    {
        path: "player",
        name: "Player",
        meta: { title: "叫号点单", icon: "el-icon-phone" },
        component: () => import("../views/common/Calling.vue")
    },
    {
        path: "editor",
        name: "Order",
        meta: { title: "点单", icon: "el-icon-document" },
        component: () => import("../views/common/Order.vue")
    },
    {
        path: "user",
        name: "User",
        hidden: true /* 不在侧边导航展示 */,
        meta: { title: "个人中心" },
        component: () => import("../views/admin/User.vue")
    },
    {
        path: "/error",
        name: "NotFound",
        hidden: true,
        meta: { title: "404" },
        component: () => import("../components/NotFound.vue")
    },
    {
        path: "/:w+",
        hidden: true,
        redirect: { name: "NotFound" }
    }
];

const routes = [
    { path: "/login", name: "Login", meta: { title: "登录" }, component: () => import("../views/login/Login.vue") },
    { path: "/", name: "Layout", meta: {title: ""},component: Layout, children: [...devMap] },
    // { path: "/", name: "CustomerLayout", meta: {title: "用户", roles: ["customer"]},component: Layout, children: [...layoutMapCustomer] },
    // { path: "/", name: "MerchantLayout", meta: {title: "商家", roles: ["merchant"]},component: Layout, children: [...layoutMapMerchant] },

];

export { routes, devMap};
