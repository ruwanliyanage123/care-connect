const HtmlWebpackPlugin = require("html-webpack-plugin");
const { ModuleFederationPlugin } = require("webpack").container;
const path = require("path");
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.tsx",

    mode: "development",

    devServer: {
        port: 4105,
        historyApiFallback: true,
        hot: true,
    },

    output: {
        publicPath: "auto",
        clean: true,
    },

    resolve: {
        extensions: [".tsx", ".ts", ".js"],
    },

    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: "ts-loader",
                exclude: /node_modules/,
            },

            // 💥 FIXED — PostCSS is now explicitly resolved
            {
                test: /\.css$/i,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        options: { importLoaders: 1 },
                    },
                    {
                        loader: "postcss-loader",
                        options: {
                            postcssOptions: {
                                config: path.resolve(__dirname, "postcss.config.js"),
                            },
                        },
                    },
                ],
            },
        ],
    },

    plugins: [
        new ModuleFederationPlugin({
            name: "care_connect_payments",
            filename: "remoteEntry.js",
            exposes: {
                "./PaymentsApp": "./src/PaymentsApp.tsx",
            },
            shared: {
                react: { singleton: true, requiredVersion: deps.react },
                "react-dom": { singleton: true, requiredVersion: deps["react-dom"] },
            },
        }),

        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};
