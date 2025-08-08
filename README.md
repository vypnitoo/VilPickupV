# VilPickupV

![VilPickupV Banner](https://i.imgur.com/your-banner.png)

A powerful Minecraft plugin that transforms how you interact with villagers. Pick up villagers with shift+right-click and place them anywhere with beautiful custom head items that preserve all their data.

## 🎯 Features

- **Smart Pickup System**: Shift+right-click villagers to convert them into portable items
- **Perfect Data Preservation**: Maintains trades, profession, level, and all villager properties
- **Custom Head Items**: Beautiful player heads with profession-specific textures
- **MySQL Database Support**: Optional data persistence with HikariCP connection pooling
- **Dual Compatibility**: EntitySnapshot API with NMS fallback for maximum compatibility
- **Zombie Villager Support**: Full equipment and data preservation
- **Baby Villager Compatible**: Special textures for baby villagers and baby zombies

## 🎮 Supported Entities

- Regular Villagers (all professions)
- Baby Villagers  
- Zombie Villagers
- Baby Zombie Villagers
- Wandering Traders

## 📦 Installation

1. Download the latest version from [SpigotMC](https://www.spigotmc.org/resources/vilpickupv.127782/)
2. Place `VilPickupV-x.x.jar` in your server's `plugins` folder
3. Restart your server
4. Configure settings in `plugins/VilPickupV/config.yml`
5. Use `/vilpickup reload` to reload configuration

## ⚙️ Configuration

Basic configuration example:
```yaml
settings:
  require-shift: true
  allowed-entities:
    - VILLAGER
    - ZOMBIE_VILLAGER
    - WANDERING_TRADER

database:
  enabled: false  # Enable MySQL data storage
  host: "localhost"
  port: 3306
  database: "vilpickup"
```

## 🔧 Commands & Permissions

**Commands:**
- `/vilpickup reload` - Reload configuration (Admin only)

**Permissions:**
- `vilpickup.use` (default: true) - Pick up villagers
- `vilpickup.admin` (default: op) - Admin commands

## 🖥️ Compatibility

- **Minecraft**: 1.19.4+
- **Server Software**: Paper (recommended), Spigot, Bukkit
- **Java**: 17+
- **Dependencies**: None required

## 📈 Version History

- **v1.8** - Fixed baby zombie villager head textures
- **v1.7** - Added MySQL database support with HikariCP
- **v1.6** - Fixed zombie villager equipment preservation
- **v1.5** - Added descriptive villager names
- **v1.4** - Fixed villager head textures using PlayerProfile API
- **v1.3** - Custom villager heads and profession preservation
- **v1.2** - Improved NMS compatibility
- **v1.1** - Added configuration system
- **v1.0** - Initial release

## 🛠️ Development

### Building
```bash
./gradlew build
```

### Testing
The plugin includes comprehensive error handling and debug logging. Enable debug mode in `config.yml`:
```yaml
debug:
  enabled: true
  log-pickups: true
  log-placements: true
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Terms of Use

- ✅ **Free to use** on your server
- ✅ **Modify** for personal use
- ✅ **Share** the original SpigotMC link
- ❌ **DO NOT RESELL** or redistribute modified versions
- ❌ **DO NOT CLAIM** as your own work
- ❌ **DO NOT REPOST** on other platforms without permission

## 🔗 Links

- **Download**: [SpigotMC Resource Page](https://www.spigotmc.org/resources/vilpickupv.127782/)
- **Issues**: [GitHub Issues](https://github.com/vypnitoo/VilPickupV/issues)
- **Developer**: [vypnito](https://github.com/vypnitoo)

## 🤝 Support

Having issues? Found a bug? Have a feature request?

1. Check the [SpigotMC discussion](https://www.spigotmc.org/resources/vilpickupv.127782/discussions/)
2. Open an issue on GitHub
3. Join our community for support

---

**Made with ❤️ by [vypnito](https://github.com/vypnitoo)**

*Please support the project by downloading from the official [SpigotMC page](https://www.spigotmc.org/resources/vilpickupv.127782/)*