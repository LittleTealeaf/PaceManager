/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    disableStaticImages: true
  },
  exportPathMap: async function(
    defaultPathMap,
    {dev,dir,outDir,distDir,buildId}
  ) {
    return {
      '/': { page: '/'},
      '/javadoc': { page: '/javadoc' }
    }
  }
}

const withPlugins = require('next-compose-plugins');

module.exports = withPlugins([
  [require('next-optimized-images'),{}]
],nextConfig)
