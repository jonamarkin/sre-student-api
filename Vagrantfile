Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/bionic64"

  # Use static IP to avoid DHCP conflicts
  config.vm.network "private_network", ip: "192.168.56.10"

  # Disable synced folders to prevent issues in WSL
  config.vm.synced_folder ".", "/vagrant", disabled: true

  config.vm.provision "shell", path: "provision.sh"

  config.vm.provider "virtualbox" do |vb|
    vb.memory = "2048"
    vb.cpus = 2
  end
end
