module.exports = {
    content: ["./public/index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {},
    },
    safelist: [
        // Background gradients
        "bg-gradient-to-br",
        "from-blue-50",
        "to-blue-100",
        "from-green-50",
        "to-green-100",
        "from-yellow-50",
        "to-yellow-100",

        // Backgrounds
        "bg-white",
        "bg-gray-50",
        "bg-blue-50",
        "bg-blue-100",
        "bg-green-100",
        "bg-yellow-100",
        "bg-red-100",
        "bg-red-500",

        // Text
        "text-white",
        "text-blue-600",
        "text-green-600",
        "text-yellow-600",
        "text-gray-600",
        "text-gray-800",
        "text-red-600",

        // Borders
        "border-gray-200",
        "border-blue-200",
        "border-green-200",
        "border-yellow-200",

        // Shadows & radius
        "shadow-sm",
        "shadow-md",
        "shadow-xl",
        "rounded-xl",
        "rounded-2xl",
    ],
    plugins: [],
};
