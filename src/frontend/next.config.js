/* const withPlugins = require("next-compose-plugins"); */

/**
 * @type {import('next').NextConfig}
 */
const nextConfig = {
    eslint: {
        ignoreDuringBuilds: true,
    },
    images: {
        disableStaticImages: true,
    },
    experimental: {
        images: {
            unoptimized: true,
        },
    },
};

module.exports = nextConfig;
