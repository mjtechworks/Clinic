import {
    CalendarEvent
} from 'angular-calendar';

export interface CalendarAppointment extends CalendarEvent {
    id: string;
}