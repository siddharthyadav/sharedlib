def call (Map config) {
    def request = libraryResource "yaml/k8s/${config?.yamlfile ?: ''}.yaml"
    writeFile file: "ns.yaml", text: "${request}"
    sh "kubectl create -f ns.yaml"
}