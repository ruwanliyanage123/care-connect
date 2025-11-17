const HtmlWebpackPlugin = require("html-webpack-plugin");
const { ModuleFederationPlugin } = require("webpack").container;
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.tsx",

    mode: "development",

    devServer: {
        port: 4100,
        historyApiFallback: true,
        hot: true
    },

    output: {
        publicPath: "auto"
    },

    resolve: {
        extensions: [".tsx", ".ts", ".js"]
    },

    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: "ts-loader",
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader", "postcss-loader"]
            }
        ]
    },

    plugins: [
        new ModuleFederationPlugin({
            name: "care_connect_root",
            remotes: {
                care_connect_dashboard: "care_connect_dashboard@http://localhost:4101/remoteEntry.js"
            },
            shared: {
                react: { singleton: true, requiredVersion: deps.react },
                "react-dom": { singleton: true, requiredVersion: deps["react-dom"] },
                "react-router-dom": { singleton: true, requiredVersion: deps["react-router-dom"] }
            }
        }),

        new HtmlWebpackPlugin({
            template: "./public/index.html"
        })
    ]
};
