import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
// import vitePluginImp from "vite-plugin-imp";
const { resolve } = require("path");

export default defineConfig({
    plugins: [
        vue()
        // vitePluginImp({
        //     libList: [
        //         {
        //             libName: "element-plus",
        //             style: name => {
        //                 return `element-plus/lib/theme-chalk/${name}.css`;
        //             }
        //         }
        //     ]
        // })
    ],
    resolve: {
        alias: [
            {
                find: "@",
                replacement: resolve(__dirname, "src")
            }
        ]
    },
    server: {
        open: true,
        cors: true
        // proxy: {
        //     "/api": {
        //         target: "https://www.baidu.com",
        //         changeOrigin: true,
        //         rewrite: path => path.replace(/^\/api/, "")
        //     }
        // }
    },
    /* 打包配置 */
    base: "./",
    build: {
        brotliSize: false,
        emptyOutDir: false,
        outDir: "dist",
        assetsDir: "static"
    }
});
