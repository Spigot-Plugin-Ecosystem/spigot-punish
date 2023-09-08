# Plugin-Template

## TODO
- [X] Package umbenennen
- [X] `pom.xml` bearbeiten
  - `artifactId`: Name des Plugins
  - `name`: Name des Plugins
  - `version`: Versionsnummer des Plugins (semantische Versionierung)
- [X] `plugin.yml` bearbeiten
  - Name des Plugins
  - package der `Main`-Klasse
- [ ] `PluginConfig` bearbeiten
  - `pluginName`: Name des Plugins
  - `requireBungeeCord`: `true`, wenn das Plugin zwangsläufig BungeeCord benötigt
  - `pluginChannels`: `true`, wenn das Plugin BungeeCord-PluginChannels verwendet
  - `mySql`: `true`, wenn das Plugin die Verwendung einer MySQL-Datenbank unterstützt
  - `requireMySql`: `true`, wenn das Plugin zwangsläufig eine MySQL-Datenbank benötigt
  - `pluginChannelEvents`: Alle `PluginChannelEvent`s, auf die das Plugin reagieren soll
  - `databaseTableUtils`: Alle `DatabaseTableUtil`s, die das Plugin verwendet
  - `commands`: Alle `Command`s, die das Plugin registrieren soll
  - `listeners`: Alle `Listener`s, die das Plugin registrieren soll
  - `gitHubUser`: GitHub-Benutzername
  - `gitHubRepo`: Name des GitHub-Repositories
- [ ] README.md anpassen

## Befehle
#### `/command` (Berechtigung: `permission`)
Description

## Technische Details
#### Unterstützte Minecraft-Versionen
1.20 - 1.20.1
