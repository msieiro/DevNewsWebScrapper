/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./pages/**/*.{js,ts,jsx,tsx,css,scss}",
        "./src/**/*.{js,ts,jsx,tsx,css,scss}",
    ],
    purge: [
        "./pages/**/*.{js,ts,jsx,tsx,css,scss}",
        "./src/**/*.{js,ts,jsx,tsx,css,scss}",
    ],
    darkMode: false, // or 'media' or 'class'
    theme: {
        extend: {},
    },
    variants: {
        extend: {},
    },
    plugins: [],
};
