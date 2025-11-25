const HtmlWebpackPlugin = require("html-webpack-plugin");
const { ModuleFederationPlugin } = require("webpack").container;
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.tsx",

    //mode: "development",
    mode: "production",

    devServer: {
        port: 4100,
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
            name: "care_connect_root",
            remotes: {
                // care_connect_dashboard: "care_connect_dashboard@http://localhost:4101/remoteEntry.js",
                // care_connect_consultants: "care_connect_consultants@http://localhost:4102/remoteEntry.js",
                // care_connect_appointments: "care_connect_appointments@http://localhost:4103/remoteEntry.js",
                // care_connect_patients: "care_connect_patients@http://localhost:4104/remoteEntry.js",
                // care_connect_payments: "care_connect_payments@http://localhost:4105/remoteEntry.js"
                care_connect_dashboard: "care_connect_dashboard@http://care-connect-dashboard-app.s3-website-us-east-1.amazonaws.com/remoteEntry.js",
                care_connect_consultants: "care_connect_consultants@http://care-connect-consultant-app.s3-website-us-east-1.amazonaws.com/remoteEntry.js",
                care_connect_appointments: "care_connect_appointments@http://care-connect-appointment-app.s3-website-us-east-1.amazonaws.com/remoteEntry.js",
                care_connect_patients: "care_connect_patients@http://care-connect-patient-app.s3-website-us-east-1.amazonaws.com/remoteEntry.js",
                care_connect_payments: "care_connect_payments@http://care-connect-payment-app.s3-website-us-east-1.amazonaws.com/remoteEntry.js"
            },
            shared: {
                react: {
                    singleton: true,
                    requiredVersion: false,
                    eager: false
                },
                "react-dom": {
                    singleton: true,
                    requiredVersion: false,
                    eager: false
                },
                "react-router-dom": {
                    singleton: true,
                    requiredVersion: false,
                    eager: false
                }
            }
        }),

        new HtmlWebpackPlugin({
            template: "./public/index.html"
        })
    ]
};
