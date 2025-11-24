const HtmlWebpackPlugin = require("html-webpack-plugin");
const { ModuleFederationPlugin } = require("webpack").container;
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.tsx",

    //mode: "development",
    mode: "production",

    devServer: {
        port: 4104,
        historyApiFallback: true,
        hot: true
    },

    output: {
        publicPath: "auto",
        clean: true
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
            name: "care_connect_patients",
            filename: "remoteEntry.js",
            exposes: {
                "./PatientsApp": "./src/PatientsApp.tsx"
            },

            shared: {
                react: {
                    singleton: true,
                    requiredVersion: deps.react
                },
                "react-dom": {
                    singleton: true,
                    requiredVersion: deps["react-dom"]
                }
            }
        }),

        new HtmlWebpackPlugin({
            template: "./public/index.html"
        })
    ]
};
