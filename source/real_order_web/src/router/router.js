import Layout from "../layout/Index.vue";
import RouteView from "../components/RouteView.vue";


// 普通用户导航
// [个人信息、商家]
const layoutMapCustomer = [
    {
        path: "customer",
        name: "Customer",
        meta: {
            title: "顾客-选择商家",
            icon: "el-icon-location",
            roles: ["customer"]
        },
        component: ()=> import("../views/custom/index.vue")
    },
]

// 商家用户导航
// [个人信息、商家]
const layoutMapMerchant = [
    {
        path: "",
        name: "Index",
        meta: {
            title: "商家主页",
            icon: "el-icon-location",
            roles: ["merchant"]
        },
        component: ()=> import("../views/merchant/index.vue")
    }
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
        meta: { title: "视频播放", icon: "el-icon-video-camera-solid" , roles: ["admin"],},
        component: () => import("../views/common/XGPlayer.vue")
    },
    {
        path: "editor",
        name: "Editor",
        meta: { title: "富文本编辑器", icon: "el-icon-s-comment" , roles: ["admin"],},
        component: () => import("../views/common/Editor.vue")
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
    { path: "/", name: "Layout", meta: {title: "管理员", roles: ["admin"]},component: Layout, children: [...layoutMap] },
    { path: "/", name: "CustomerLayout", meta: {title: "用户", roles: ["customer"]},component: Layout, children: [...layoutMapCustomer] },
    { path: "/", name: "MerchantLayout", meta: {title: "商家", roles: ["merchant"]},component: Layout, children: [...layoutMapMerchant] },

];

export { routes, layoutMap, layoutMapCustomer, layoutMapMerchant };
