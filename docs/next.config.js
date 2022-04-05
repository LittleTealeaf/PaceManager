const withPlugins = require('next-compose-plugins');


/** @type {import('next').NextConfig} */
const nextConfig = {
  webpack: (config, { buildId, dev, isServer, defaultLoaders, webpack }) => {
    config.module.rules.push({
      test: /\.html$/i,
      use: [{
        loader: "html-loader",
        options:{}
      }]
    })
    // Important: return the modified config
    return config
  },
  reactStrictMode: true,
  images: {
    disableStaticImages: true
  },
  basePath: '/paceManager'
}

module.exports = withPlugins([
  [require('next-optimized-images'),{}]
],nextConfig);

