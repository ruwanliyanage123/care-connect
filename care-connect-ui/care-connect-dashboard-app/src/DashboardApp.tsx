import React from "react";
import "./global.css";

export default function DashboardApp() {
    return (
        <div className="p-6 bg-gray-100 rounded-lg shadow">
            <h2 className="text-2xl font-bold mb-4 text-sky-700">Dashboard Overview</h2>

            <div className="grid grid-cols-3 gap-4">
                <div className="p-4 bg-white border rounded shadow">
                    <h3 className="font-semibold">Patients</h3>
                    <p className="text-3xl text-sky-700">152</p>
                </div>

                <div className="p-4 bg-white border rounded shadow">
                    <h3 className="font-semibold">Consultants</h3>
                    <p className="text-3xl text-sky-700">38</p>
                </div>

                <div className="p-4 bg-white border rounded shadow">
                    <h3 className="font-semibold">Appointments Today</h3>
                    <p className="text-3xl text-sky-700">21</p>
                </div>
            </div>
        </div>
    );
}
