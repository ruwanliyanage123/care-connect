module.exports = {
    content: ["./src/**/*.{js,jsx,ts,tsx,html}"],
    theme: {
        extend: {
            colors: {
                healthcare: {
                    primary: "#0EA5E9",   // sky-500
                    primaryDark: "#0369A1", // sky-700
                    success: "#0D9488",   // teal-600
                    positive: "#10B981",  // green-500
                    danger: "#EF4444",    // red-500
                    text: "#334155",      // slate-700
                    muted: "#64748B",     // slate-500
                    bg: "#F1F5F9"         // gray-100
                }
            }
        }
    },
    plugins: []
};
