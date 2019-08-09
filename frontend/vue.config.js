module.exports = {
	runtimeCompiler: true,
	css: {
		sourceMap: true,
	},
	devServer: {
		port: 8082,
		proxy: {
			"/good-anime": {
				target: "http://localhost:8081",
				secure: false,
			},
		},
	},
};
