#!/usr/bin/env python3
"""
VilPickupV Plugin Update Automation
Reads CLAUDE.md for current version and automates the update process
"""

import re
import subprocess
import shutil
import os
from pathlib import Path

# Paths
PROJECT_DIR = Path(__file__).parent
CLAUDE_MD = PROJECT_DIR / "CLAUDE.md"
PLUGIN_YML = PROJECT_DIR / "src" / "main" / "resources" / "plugin.yml"
BUILD_DIR = PROJECT_DIR / "build" / "libs"
SERVER_PLUGINS = Path("C:/Users/jenik/Desktop/1.21.4/plugins")

def get_current_version():
    """Read current version from CLAUDE.md"""
    with open(CLAUDE_MD, 'r') as f:
        content = f.read()
    
    match = re.search(r'\*\*(\d+\.\d+)\*\*', content)
    return match.group(1) if match else "1.0"

def increment_version(version):
    """Increment version number (1.1 -> 1.2)"""
    major, minor = version.split('.')
    return f"{major}.{int(minor) + 1}"

def update_version_files(old_version, new_version):
    """Update version in plugin.yml and CLAUDE.md"""
    # Update plugin.yml
    with open(PLUGIN_YML, 'r') as f:
        content = f.read()
    content = re.sub(r"version: '[^']*'", f"version: '{new_version}'", content)
    with open(PLUGIN_YML, 'w') as f:
        f.write(content)
    
    # Update CLAUDE.md
    with open(CLAUDE_MD, 'r') as f:
        content = f.read()
    content = re.sub(r'\*\*\d+\.\d+\*\*', f"**{new_version}**", content)
    with open(CLAUDE_MD, 'w') as f:
        f.write(content)
    
    print(f"Updated version from {old_version} to {new_version}")

def build_plugin():
    """Build plugin using Gradle"""
    print("Building plugin...")
    result = subprocess.run(["./gradlew", "build"], cwd=PROJECT_DIR, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Build failed: {result.stderr}")
        return False
    print("Build successful!")
    return True

def deploy_plugin(version):
    """Copy plugin jar to server folder"""
    jar_file = BUILD_DIR / f"VilPickupV-{version}.jar"
    if not jar_file.exists():
        # Try with previous version name format
        jar_file = list(BUILD_DIR.glob("VilPickupV-*.jar"))[0]
    
    target_file = SERVER_PLUGINS / f"VilPickupV-{version}.jar"
    
    shutil.copy2(jar_file, target_file)
    print(f"Deployed {jar_file.name} to {target_file}")
    
    # List old versions for cleanup
    old_jars = list(SERVER_PLUGINS.glob("VilPickupV-*.jar"))
    if len(old_jars) > 1:
        print("Old versions found (consider removing):")
        for jar in old_jars:
            if jar.name != f"VilPickupV-{version}.jar":
                print(f"  - {jar.name}")

def main():
    """Main update process"""
    print("=== VilPickupV Update Automation ===")
    
    current_version = get_current_version()
    new_version = increment_version(current_version)
    
    print(f"Current version: {current_version}")
    print(f"New version: {new_version}")
    
    # Update version files
    update_version_files(current_version, new_version)
    
    # Build plugin
    if not build_plugin():
        return
    
    # Deploy to server
    deploy_plugin(new_version)
    
    print(f"\n✅ Plugin updated to v{new_version} and deployed!")
    print("Remember to restart the server to load the new version.")

if __name__ == "__main__":
    main()