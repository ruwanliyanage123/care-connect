const HtmlWebpackPlugin = require("html-webpack-plugin");
const { ModuleFederationPlugin } = require("webpack").container;
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.tsx",

    mode: "development",

    devServer: {
        port: 4101,
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
            name: "care_connect_dashboard",
            filename: "remoteEntry.js",
            exposes: {
                "./DashboardApp": "./src/DashboardApp.tsx"
            },
            shared: {
                react: { singleton: true, requiredVersion: deps.react },
                "react-dom": { singleton: true, requiredVersion: deps["react-dom"] }
            }
        }),

        new HtmlWebpackPlugin({
            template: "./public/index.html"
        })
    ]
};
