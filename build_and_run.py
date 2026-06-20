import glob
import subprocess
import os
import sys
import shutil

def main():
    # Clean build directory to force recompilation
    if os.path.exists('build'):
        print("Cleaning build directory...")
        try:
            shutil.rmtree('build')
            print("Build directory cleaned.")
        except Exception as e:
            print(f"Warning: could not fully clean build directory: {e}")
            
    print("Finding all Java source files...")
    files = glob.glob('src/**/*.java', recursive=True)
    with open('sources.txt', 'w') as f:
        f.write('\n'.join(files))
    print(f"Written {len(files)} files to sources.txt")
    
    # Create classes directory
    os.makedirs('build/classes', exist_ok=True)
    
    # Compile using javac
    print("Compiling sources...")
    compile_cmd = [
        "javac",
        "--release", "21",
        "-d", "build/classes",
        "-cp", "lib/mysql-connector-j-8.0.33.jar;lib/AbsoluteLayout.jar",
        "@sources.txt"
    ]
    res = subprocess.run(compile_cmd, shell=True)
    if res.returncode != 0:
        print("Compilation failed!")
        sys.exit(res.returncode)
    print("Compilation successful.")
    
    # Copy images to classpath
    src_images = 'src/images'
    dest_images = 'build/classes/images'
    if os.path.exists(src_images):
        print("Copying image resources...")
        try:
            shutil.copytree(src_images, dest_images, dirs_exist_ok=True)
            print("Image resources copied successfully.")
        except Exception as e:
            print(f"Error copying images: {e}")
            sys.exit(1)
            
    # Run CaptureDashboard
    print("Running CaptureDashboard...")
    run_cmd = [
        "java",
        "-cp", "build/classes;lib/mysql-connector-j-8.0.33.jar;lib/AbsoluteLayout.jar",
        "view.CaptureDashboard"
    ]
    res2 = subprocess.run(run_cmd, shell=True)
    if res2.returncode != 0:
        print("Running CaptureDashboard failed!")
        sys.exit(res2.returncode)
    print("CaptureDashboard completed successfully.")
    
    # Run CapturePayment
    print("Running CapturePayment...")
    run_cmd_payment = [
        "java",
        "-cp", "build/classes;lib/mysql-connector-j-8.0.33.jar;lib/AbsoluteLayout.jar",
        "view.CapturePayment"
    ]
    res3 = subprocess.run(run_cmd_payment, shell=True)
    if res3.returncode != 0:
        print("Running CapturePayment failed!")
        sys.exit(res3.returncode)
    print("CapturePayment completed successfully.")
    
    # Run CaptureLogin
    print("Running CaptureLogin...")
    run_cmd_login = [
        "java",
        "-cp", "build/classes;lib/mysql-connector-j-8.0.33.jar;lib/AbsoluteLayout.jar",
        "view.CaptureLogin"
    ]
    res4 = subprocess.run(run_cmd_login, shell=True)
    if res4.returncode != 0:
        print("Running CaptureLogin failed!")
        sys.exit(res4.returncode)
    print("CaptureLogin completed successfully.")


if __name__ == '__main__':
    main()
